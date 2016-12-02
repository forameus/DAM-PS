<?php

require_once "Constantes.php";


class ProductoHandlerModel
{
    public static function getProducto($cod)
    {
        $listaProductos = null;

        $db = DatabaseModel::getInstance();
        $db_connection = $db->getConnection();

        $valid = self::isValid($cod);

        if ($valid === true || $cod == null) {
            $query = "SELECT " . \ConstantesDB\Constantes::COD . ","
                . \ConstantesDB\Constantes::NOMBRE . "," . \ConstantesDB\Constantes::DESCRIPCION . "," . \ConstantesDB\Constantes::PRECIO . " FROM " . \ConstantesDB\Constantes::TABLA;


            if ($cod != null) {
                $query = $query . " WHERE " . \ConstantesDB\Constantes::COD . " = ?";
            }

            $prep_query = $db_connection->prepare($query);


            if ($cod != null) {
                $prep_query->bind_param('i', $cod);
            }

            $prep_query->execute();
            $listaProductos = array();


            $prep_query->bind_result($cod, $nombre, $descripcion, $precio);
            while ($prep_query->fetch()) {
                $nombre = utf8_encode($nombre);
                $descripcion = utf8_encode($descripcion);
                $producto = new ProductoModel($cod, $nombre, $descripcion, $precio);
                $listaProductos[] = $producto;
            }


        }
        $db_connection->close();

        return $listaProductos;
    }


    public static function putProducto($producto)
    {
        $db = DatabaseModel::getInstance();
        $db_connection = $db->getConnection();

        $code = '200';

        if ($producto == null)
            $code = '400';
        else {

            //Comprobar si ya existe un producto con el mismo cod
            $select = "SELECT " . \ConstantesDB\Constantes::COD . ","
                . \ConstantesDB\Constantes::NOMBRE . "," . \ConstantesDB\Constantes::DESCRIPCION . "," . \ConstantesDB\Constantes::PRECIO . " FROM " . \ConstantesDB\Constantes::TABLA . " WHERE " . \ConstantesDB\Constantes::COD . " = " . $producto->cod . ";";


            $result = $db_connection->query($select);


            //Si existe, se crea
            if ($result->num_rows > 0) {
                $query = " UPDATE " . \ConstantesDB\Constantes::TABLA . " SET " . \ConstantesDB\Constantes::NOMBRE . " = '" . $producto->nombre . "', " . \ConstantesDB\Constantes::DESCRIPCION . " = '" . $producto->descripcion . "' , " . \ConstantesDB\Constantes::PRECIO . "= " . $producto->precio . " WHERE " . \ConstantesDB\Constantes::COD . " = " . $producto->cod . ";";
                $db_connection->query($query);

                //Si no, se inserta uno nuevo
            } else {
                $query = "INSERT INTO " . \ConstantesDB\Constantes::TABLA . " VALUES (NULL, '" . $producto->nombre . " ', '" . $producto->descripcion . "' , " . $producto->precio . "); ";

                $db_connection->query($query);

            }

        }
        $db_connection->close();

        return $code;

    }


    public static function isValid($cod)
    {
        $res = false;

        if (ctype_digit($cod)) {
            $res = true;
        }
        return $res;
    }

}
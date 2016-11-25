<?php

require_once "Constantes.php";


class AutorHandlerModel
{

    public static function getAutor($id)
    {
        $listaAutores = null;

        $db = DatabaseModel::getInstance();
        $db_connection = $db->getConnection();

        $valid = self::isValid($id);

        if ($valid === true || $id == null) {
            $query = "SELECT " . \ConstantesDB\Constantes::ID . ","
                . \ConstantesDB\Constantes::NOMBRE .",". \ConstantesDB\Constantes::APELLIDOS . " FROM " . \ConstantesDB\Constantes::TABLA_AUTORES;


            if ($id != null) {
                $query = $query . " WHERE " . \ConstantesDB\Constantes::ID . " = ?";
            }

            $prep_query = $db_connection->prepare($query);



            if ($id != null) {
                $prep_query->bind_param('i', $id);
            }

            $prep_query->execute();
            $listaAutores = array();


            $prep_query->bind_result($id, $nombre, $apellidos);
            while ($prep_query->fetch()) {
                $nombre = utf8_encode($nombre);
                $apellidos = utf8_encode($apellidos);
                $autor = new AutorModel($id, $nombre, $apellidos);
                $listaAutores[] = $autor;
            }


        }
        $db_connection->close();

        return $listaAutores;
    }


    public static function isValid($id)
    {
        $res = false;

        if (ctype_digit($id)) {
            $res = true;
        }
        return $res;
    }

}
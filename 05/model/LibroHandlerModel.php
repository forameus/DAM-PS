<?php

require_once "Constantes.php";


class LibroHandlerModel
{

    public static function getLibro($id)
    {
        $listaLibros = null;

        $db = DatabaseModel::getInstance();
        $db_connection = $db->getConnection();

        $valid = self::isValid($id);

        //If the $id is valid or the client asks for the collection ($id is null)
        if ($valid === true || $id == null) {
            $query = "SELECT " . \ConstantesDB\Constantes::ID . ","
                . \ConstantesDB\Constantes::TITULO . " FROM " . \ConstantesDB\Constantes::TABLA_LIBROS;


            if ($id != null) {
                $query = $query . " WHERE " . \ConstantesDB\Constantes::ID . " = ?";
            }

            $prep_query = $db_connection->prepare($query);

            //IMPORTANT: If we do not want to expose our primary keys in the URIS,
            //we can use a function to transform them.
            //For example, we can use hash_hmac:
            //http://php.net/manual/es/function.hash-hmac.php
            //In this example we expose primary keys considering pedagogical reasons

            if ($id != null) {
                $prep_query->bind_param('s', $id);
            }

            $prep_query->execute();
            $listaLibros = array();

            //IMPORTANT: IN OUR SERVER, I COULD NOT USE EITHER GET_RESULT OR FETCH_OBJECT,
            // PHP VERSION WAS OK (5.4), AND MYSQLI INSTALLED.
            // PROBABLY THE PROBLEM IS THAT MYSQLND DRIVER IS NEEDED AND WAS NOT AVAILABLE IN THE SERVER:
            // http://stackoverflow.com/questions/10466530/mysqli-prepared-statement-unable-to-get-result

            $prep_query->bind_result($cod, $tit);
            while ($prep_query->fetch()) {
                $tit = utf8_encode($tit);
                $libro = new LibroModel($cod, $tit);
                $listaLibros[] = $libro;
            }

//            $result = $prep_query->get_result();
//            for ($i = 0; $row = $result->fetch_object(LibroModel::class); $i++) {
//
//                $listaLibros[$i] = $row;
//            }
        }
        $db_connection->close();

        return $listaLibros;
    }

    //returns true if $id is a valid id for a book
    //In this case, it will be valid if it only contains
    //numeric characters, even if this $id does not exist in
    // the table of books
    public static function isValid($id)
    {
        $res = false;

        if (ctype_digit($id)) {
            $res = true;
        }
        return $res;
    }



    public static function putLibro($listaLibros)
    {
        $db = DatabaseModel::getInstance();
        $db_connection = $db->getConnection();

        if ($listaLibros != null) {
            if(count($listaLibros) > 1) {
                foreach ($listaLibros as $titulo => $valor) {
                    $query = "INSERT INTO " . \ConstantesDB\Constantes::TABLA_LIBROS . " VALUES (NULL, '" . $valor->titulo . "' ); ";

                    $db_connection->query($query);
                }
            }
            else{
                $query = "INSERT INTO " . \ConstantesDB\Constantes::TABLA_LIBROS . " VALUES (NULL, '" . $listaLibros[0] . "' ); ";

                $db_connection->query($query);
            }


        }

        //$query = "INSERT INTO " . \ConstantesDB\Constantes::TABLA_LIBROS . " VALUES (NULL, '" . $listaLibros . "' ); ";

        //$db_connection->query($query);
        $db_connection->close();
    }

    public static function deleteLibro($id)
    {
        $db = DatabaseModel::getInstance();
        $db_connection = $db->getConnection();

        if ($id != null){

            $query = "INSERT INTO " . \ConstantesDB\Constantes::TABLA_LIBROS . " VALUES (NULL, '" . $listaLibros[0] . "' ); ";

            $db_connection->query($query);

        }

        //$query = "INSERT INTO " . \ConstantesDB\Constantes::TABLA_LIBROS . " VALUES (NULL, '" . $listaLibros . "' ); ";

        //$db_connection->query($query);
        $db_connection->close();
    }
}
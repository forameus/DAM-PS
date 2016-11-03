<?php
/**
 * Created by PhpStorm.
 * User: anavarro
 * Date: 21/10/16
 * Time: 9:14
 */

if(empty($_POST["id"])){
    echo '
    <form action="eliminaAlumno.php" method="post">
        <h3>Añadir Alumno</h3>
        <p>Introduzca la ID del alumno a eliminar:</p>
        ID:  <input type="text" required="required" name="id"/>       
        <br/><br/>  
        <input type="submit"/>    
    </form>   
    ';
}
else {
    //Conectarse a la BD
    require_once "database.php";
    require_once "tablaAlumnos.php";

    $db = Database::getInstance();
    $mysqli = $db->getConnection();


    $insertar = "DELETE FROM Alumnos WHERE id=\" " . $_POST["id"]."\";";

    if ($mysqli->query($insertar) === TRUE) {
        header('Refresh: 3; URL=index.php');
        echo '<a>El alumno ha sido eliminado correctamente. Volviendo al inicio...</a>';
    } else {
        echo "Error: " . $mysqli->error;
    }
}
<?php
/**
 * Created by PhpStorm.
 * User: anavarro
 * Date: 20/10/16
 * Time: 10:55
 */
if(empty($_POST["nombre"])){
    echo '
    <form action="anadeAlumno.php" method="post">
        <h3>Añadir Alumno</h3>
        <p>Introduzca los datos del alumno:</p>
        Nombre:  <input type="text" required="required" name="nombre"/>       
        <br/><br/>
        Apellidos: <input type="text" required="required" name="apellidos"/>
        <br/><br/>    
        <input type="submit"/>    
    </form>   
    ';
}
else{
    //Conectarse a la BD
    require_once "database.php";
    require_once "tablaAlumnos.php";

    $db = Database::getInstance();
    $mysqli = $db->getConnection();

    $insertar = "INSERT INTO Alumnos (nombre, apellidos) VALUES (\" ".$_POST["nombre"]." \",\" ".$_POST["apellidos"]."\");";

    if ($mysqli->query($insertar) === TRUE) {
        header('Refresh: 3; URL=index.php');
        echo '<a>El alumno '.$_POST["nombre"].' '.$_POST["apellidos"].' ha sido añadido correctamente. Volviendo al inicio...</a>';
    } else {
        echo "Error: " . $mysqli->error;
    }
}





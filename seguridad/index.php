<?php

$html =  '<!doctype html>
            <html lang="es">
            <head>
                <meta charset="UTF-8">
                <title>Log In</title>
            </head>
            
            <form method="post">
                    <div>
                        <label><b>Usuario:</b></label><br/>
                        <input type="text" placeholder="Usuario" name="user" required>
                        <br/>
                        <label><b>Contraseña:</b></label><br/>
                        <input type="password" placeholder="Contraseña" name="pass" required><br/><br/>
                        
                        <input type="submit" name="send" value="Entrar"/>
                    </div>
                </form>            
                <br/>
                <a href="registro.php">Registrarse</a>';

if (isset($_POST["user"])) {

    $mysqli = new mysqli("localhost", "root","","Biblioteca");
    $sql_query = "SELECT Username, Password FROM Usuarios WHERE Username='".$_POST["user"]."';";
    $result = $mysqli->query($sql_query);
    if ($result->num_rows > 0) {
        $row = $result->fetch_array();
        if(password_verify($_POST["pass"], $row[1]))
            header("Location:http://www.google.es");
        else
            echo 'Contraseña no válida';

    }
    else
        echo '<h3 style="color: red">Usuario no encontrado</h3>'.$html;
    $mysqli->close();
} else {
    echo $html;
}
?>


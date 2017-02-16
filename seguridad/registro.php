<?php
$html = '<!doctype html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Registro</title>
</head>
<body>
<form method="post">
    <h1>REGISTRASIÓN</h1>
    <div>
        <label><b>Usuario:</b></label><br/>
        <input type="text" placeholder="Usuario" name="user" required>
        <br/>
        <label><b>Contraseña:</b></label><br/>
        <input type="password" placeholder="Contraseña" name="pass1" required><br/>
        <label><b>Contraseña otra vez:</b></label><br/>
        <input type="password" placeholder="Contraseña otra vez" name="pass2" required><br/><br/>
        <input type="submit" name="send" value="Entrar"/>
    </div>
</form>
</body>
</html>';


if (isset($_POST["user"])) {
    if ($_POST["pass1"] == $_POST["pass2"]) {
        $conn = new mysqli("localhost", "root", "", "Biblioteca");

        if ($conn->connect_error) {
            die("Connection failed: " . $conn->connect_error);
        }
        //Comprobar si el usuario existe
        $sql_query = "SELECT Username, Password FROM Usuarios WHERE Username='" . $_POST["user"] . "';";
        $result = $conn->query($sql_query);
        if ($result->num_rows > 0)
            echo '<h3 style="color: red">Error: ya existe un usuario con ese nombre</h3>'.$html;
        else {

            //Insertar el usuario
            $sql = "INSERT INTO Usuarios VALUES (null,'" . $_POST["user"] . "', '" .password_hash($_POST["pass1"], PASSWORD_DEFAULT) . "')";
            if ($conn->query($sql) === TRUE) {
                echo "<p>Se ha registrado correctamente<p>";
                echo "<a href='index.php'>Volver al login<a>";
            } else {
                echo "Error: " . $sql . "<br>" . $conn->error;
            }
        }

        $conn->close();
    } else {
        echo '<h3 style="color: red">Error, las contraseñas no coinciden</h3>';
        echo $html;
    }
} else echo $html;
?>




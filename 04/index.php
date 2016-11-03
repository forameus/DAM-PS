<?php

echo '<html><head><meta charset="UTF-8"/><title>ALUMNOS</title></head><body><h1>ALUMNOS</h1>';

    require_once "database.php";
    require_once "tablaAlumnos.php";

        $db = Database::getInstance();
        $mysqli = $db->getConnection();
        $sql_query = "SELECT ". \Constantes_DB\tablaAlumnos::ID . " , "
                              . \Constantes_DB\tablaAlumnos::NOMBRE . " , "
                              . \Constantes_DB\tablaAlumnos::APELLIDOS . " "
                      ." FROM ". \Constantes_DB\tablaAlumnos::TABLE_NAME;


        $result = $mysqli->query($sql_query);

        if ($result->num_rows > 0) {
            echo '<table border=\"1\">';
            echo '<tr>';
            echo '<td>'. \Constantes_DB\tablaAlumnos::ID  .'</td>';
            echo '<td>'. \Constantes_DB\tablaAlumnos::NOMBRE  .'</td>';
            echo '<td>'. \Constantes_DB\tablaAlumnos::APELLIDOS .'</td>';
            echo '</tr>';

            // mostrar cada fila
            while($row = $result->fetch_assoc()) {
                echo '<tr>';
                echo '<td>'. $row[\Constantes_DB\tablaAlumnos::ID]  .'</td>';
                echo '<td>'. $row[\Constantes_DB\tablaAlumnos::NOMBRE]  .'</td>';
                echo '<td>'. $row[\Constantes_DB\tablaAlumnos::APELLIDOS] .'</td>';
                echo '</tr>';
            }
            echo '</table>';

       echo'
      <a href="./anadeAlumno.php"><button>Añadir</button></a>
      <a href="./eliminaAlumno.php"><button>Eliminar</button></a>
  </body>        
</html>';
} ?>
    
    
    
    

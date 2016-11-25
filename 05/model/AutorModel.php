<?php


class AutorModel implements JsonSerializable
{

    private $nombre;
    private $apellidos;
    private $id;

    public function __construct($id, $nombre, $apellidos)
    {
        $this->nombre = $nombre;
        $this->apellidos = $apellidos;
        $this->id = $id;
    }

    public function getNombre()
    {
        return $this->nombre;
    }


    public function setNombre($nombre)
    {
        $this->nombre = $nombre;
    }

    public function getApellidos()
    {
        return $this->apellidos;
    }

    public function setApellidos($apellidos)
    {
        $this->apellidos = $apellidos;
    }

    public function getId()
    {
        return $this->id;
    }

    public function setId($id)
    {
        $this->id = $id;
    }



    function jsonSerialize()
    {
        return array(
            'id' => $this->id,
            'nombre' => $this->nombre,
            'apellidos' => $this->apellidos
        );
    }


    public function __sleep(){
        return array('id','nombre','apellidos');
    }

}
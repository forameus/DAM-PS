<?php


class ProductoModel implements JsonSerializable
{

    public $nombre;
    public $descripcion;
    public $precio;
    public $cod;

    public function __construct($cod, $nombre, $descripcion, $precio)
    {
        $this->nombre = $nombre;
        $this->descripcion = $descripcion;
        $this->cod = $cod;
        $this->precio = $precio;
    }

    public function getNombre()
    {
        return $this->nombre;
    }


    public function setNombre($nombre)
    {
        $this->nombre = $nombre;
    }

    public function getDescripcion()
    {
        return $this->descripcion;
    }

    public function setDescripcion($descripcion)
    {
        $this->descripcion = $descripcion;
    }

    public function getPrecio()
    {
        return $this->precio;
    }

    public function setPrecio($precio)
    {
        $this->precio = $precio;
    }

    public function getCod()
    {
        return $this->cod;
    }

    public function setCod($cod)
    {
        $this->cod = $cod;
    }



    function jsonSerialize()
    {
        return array(
            'cod' => $this->cod,
            'nombre' => $this->nombre,
            'descripcion' => $this->descripcion,
            'precio' => $this->precio
        );
    }


    public function __sleep(){
        return array('cod','nombre','descripcion','precio');
    }

}
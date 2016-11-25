<?php

require_once "Controller.php";


class AutorController extends Controller
{
    public function manageGetVerb(Request $request)
    {

        $listaAutores = null;
        $id = null;
        $response = null;
        $code = null;

        if (isset($request->getUrlElements()[2])) {
            $id = $request->getUrlElements()[2];
        }


        $listaAutores = AutorHandlerModel::getAutor($id);

        if ($listaAutores != null) {
            $code = '200';

        } else {
            echo 'Autores es nulo';

            if (AutorHandlerModel::isValid($id)) {
                $code = '404';
            } else {
                $code = '400';
            }

        }

        $response = new Response($code, null, $listaAutores, $request->getAccept());
        $response->generate();

    }

}
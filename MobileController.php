<?php

namespace App\Controller;


use App\Entity\Oeuvre;
use App\Form\OeuvreType;
use Doctrine\ORM\EntityManagerInterface;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Serializer\SerializerInterface;
use Symfony\Component\Serializer\Serializer;
use Symfony\Component\Serializer\Normalizer\ObjectNormalizer;
use Symfony\Component\HttpFoundation\JsonResponse;
use Symfony\Component\Serializer\Encoder\JsonEncoder;
use App\Repository\oeuvreRepository;
use Symfony\Component\Routing\Annotation\Route;

class MobileController extends AbstractController
{
    /**
     * @Route("/mobile", name="mobile")
     */
    public function index(): Response
    {
        return $this->render('mobile/index.html.twig', [
            'controller_name' => 'MobileController',
        ]);
    }

    /**
     * @Route("/listOeuvre", name="listOeuvre")
     */
    public function getAllOeuvre()
    {
        $event = $this->getDoctrine()->getManager()->getRepository(Oeuvre::class)->findAll();
        $serializer = new Serializer([new ObjectNormalizer()]);
        $formatted = $serializer->normalize($event);

        return new JsonResponse($formatted);

    }

    /**
     * @Route("/addOeuvre")
     */
    public function addOeuvre(Request $request):Response
    {   $oeuvre = new Oeuvre();
        $nomoeuvre= $request->get("nomoeuvre");
        $prixoeuvre = $request->get("prixoeuvre");
       // $dateoeuvre = new \DateTime('now');
     //   $imageFile = $request->get("imageoeuvre");
        $nomcat = $request->get("nomcat");
        $emailartiste = $request->get("emailartiste");
        //$idArtiste = $request->get("Id_Artiste");



        $oeuvre->setNomoeuvre($nomoeuvre);
        $oeuvre->setPrixoeuvre($prixoeuvre);
       // $oeuvre->setDateoeuvre($dateoeuvre);
        //$oeuvre->setImageoeuvre($imageFile);
        $oeuvre->setNomcat($nomcat);
        $oeuvre->setEmailartiste($emailartiste);
        //$oeuvre->setIdArtiste($idArtiste);


            $em = $this->getDoctrine()->getManager();
            $em->persist($oeuvre);
            $em->flush();


            return new Response('Oeuvre added successfully' . json_encode($oeuvre));

        }

    /**
     * @Route("/deleteOeuvre", name="deleteOeuvre", methods={"GET"})
     */
    public function delete(Request $request)
    {

        $id = $request->get("ID_Oeuvre");

        $em = $this->getDoctrine()->getManager();
        $oeuvre = $em->getRepository(Oeuvre::class)->find($id);
        if($oeuvre!=null ) {
            $em->remove($oeuvre);
            $em->flush();

            $serialize = new Serializer([new ObjectNormalizer()]);
            $formatted = $serialize->normalize("Oeuvre deleted successfully.");
            return new JsonResponse($formatted);

        }
        return new JsonResponse("Invalid name.");


    }

}
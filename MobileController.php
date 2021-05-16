<?php

namespace App\Controller;

use App\Entity\Event;
use App\Repository\EventRepository;
use Doctrine\ORM\EntityManagerInterface;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Component\Serializer\Normalizer\NormalizerInterface;
use Symfony\Component\Serializer\SerializerInterface;
use  Symfony\Component\Serializer\Serializer;
use Symfony\Component\Serializer\Normalizer\ObjectNormalizer;
use Symfony\Component\HttpFoundation\JsonResponse;
use Symfony\Component\Serializer\Encoder\JsonEncoder;

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
     * @Route("/listEvent", name="listEvent")
     */
    public function getAllEvents()
    {
        $event = $this->getDoctrine()->getManager()->getRepository(Event::class)->SortEvent();
        $serializer = new Serializer([new ObjectNormalizer()]);
        $formatted = $serializer->normalize($event);

        return new JsonResponse($formatted);

    }

    /**
     * @Route("/addEvent", name="addEvent")
     */
    public function addEvent(Request $request, SerializerInterface $serializer, EntityManagerInterface $em):Response
    {
        $content=$request->getContent();
        $data=$serializer->deserialize($content,Event::class,'json');
        $em->persist($data);
        $em->flush();
        return new Response('Event added successfully'.json_encode($data));
    }

    /**
     * @Route("/deleteEvent/{codeEvent}", name="deleteEvent", methods={"GET"})
     */
    public function delete(Request $request)
    {

        $id = $request->get("codeEvent");

        $em = $this->getDoctrine()->getManager();
        $event = $em->getRepository(Event::class)->find($id);
        if($event!=null ) {
            $em->remove($event);
            $em->flush();

            $serialize = new Serializer([new ObjectNormalizer()]);
            $formatted = $serialize->normalize("Event deleted successfully.");
            return new JsonResponse($formatted);

        }
        return new JsonResponse("Invalid event code.");


    }

    /**
     * @Route("/searchEvent/{nomEvent} ", name="searchEvent")
     */
    public function searchEventx(Request $request,NormalizerInterface $Normalizer)
    {

        $repository = $this->getDoctrine()->getRepository(Event::class);
        $requestString=$request->get("nomEvent");
        $events = $repository->findEventByName($requestString);
        $jsonContent = $Normalizer->normalize($events, 'json',['groups'=>'events:read']);
        $retour=json_encode($jsonContent);
        return new Response($retour);

    }

    /**
     * @Route("/searchEvent/{nomEvent}", name="searchEvent")
     */
    public function searchEvent(Request $request)
    {
        $event = $this->getDoctrine()->getManager()->getRepository(Event::class)->findEventByName($request->get("nomEvent"));
        $serializer = new Serializer([new ObjectNormalizer()]);
        $formatted = $serializer->normalize($event);

        return new JsonResponse($formatted);

    }

}

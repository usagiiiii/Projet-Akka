import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import spray.json._

final case class Message(message: String)

trait JsonFormats extends SprayJsonSupport with DefaultJsonProtocol {
  implicit val messageFormat: RootJsonFormat[Message] = jsonFormat1(Message)
  implicit val userFormat = jsonFormat4(Utilisateur) // Adapter selon tes champs
}

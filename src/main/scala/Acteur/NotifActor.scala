import akka.actor.{Actor, Props}
import scala.concurrent.ExecutionContext.Implicits.global

class NotificationActor extends Actor {

  def receive: Receive = {
    case CreateNotification(notif) =>
      val senderRef = sender()
      NotifDAO.insert(notif).map(senderRef ! _)

    case GetAllNotifications =>
      val senderRef = sender()
      NotifDAO.getAll().map(senderRef ! _)

    case MarkAsRead(id) =>
      val senderRef = sender()
      val query = NotifDAO.notifications.filter(_.id === id).map(_.lu).update(true)
      NotifDAO.db.run(query).map(senderRef ! _)

    case GetNotificationsByUser(userId) =>
      val senderRef = sender()
      val query = NotifDAO.notifications.filter(_.utilisateur_id === userId).result
      NotifDAO.db.run(query).map(senderRef ! _)
  }
}

object NotificationActor {
  def props: Props = Props[NotificationActor]
}

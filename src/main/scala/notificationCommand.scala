sealed trait NotificationCommand
case class CreateNotification(notif: Notifications) extends NotificationCommand
case object GetAllNotifications extends NotificationCommand
case class MarkAsRead(id: Int) extends NotificationCommand
case class GetNotificationsByUser(userId: Int) extends NotificationCommand

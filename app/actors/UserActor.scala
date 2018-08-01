package actors

import akka.actor.Actor

class UserActor extends Actor {
  override def receive: Receive = {
    case s: String => println(s"User ------  $s")
    case i: Integer => println(s"Number ------  $i")
  }
}

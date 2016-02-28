package models

import org.joda.time.DateTime

/**
  * Created by corpus on 25/02/2016.
  */
case class Publication(
                        id: Option[Long],
                        name: String,
                        description: String,
                        creatorId: Long,
                        creationDate: DateTime

                      )

case class PublicationMember(

                              publication_name: String,
                              member_id: Long,
                              join_date: DateTime,
                              title: String

                            )
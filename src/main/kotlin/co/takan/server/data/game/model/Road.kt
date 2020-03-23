package co.takan.server.data.game.model

import javax.persistence.*

@Entity
data class Road(
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long = -1,
    @ManyToOne val t1: Tile? = null,
    @ManyToOne val t2: Tile? = null
)
package co.takan.server.data.game.model

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

enum class TileType {
  OCEAN,
  DESERT,
  FOREST,
  MINE, /* hills */
  PASTURE,
  PLAIN /* field */,
  QUARRY /* mountain */;

  companion object {
      val landTiles = values().filter { it != OCEAN }
  }
}

val TileType.numberOfTiles: Int
  get() = when (this) {
    TileType.OCEAN   -> 18
    TileType.DESERT  -> 1
    TileType.FOREST  -> 4
    TileType.MINE    -> 3
    TileType.PASTURE -> 4
    TileType.PLAIN   -> 4
    TileType.QUARRY  -> 3
  }


@Entity
class Tile(
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long = -1,
    var type: String = TileType.FOREST.name,
    var value: Int = 0
) {
  override fun toString(): String {
    return "Tile(id=$id, type='$type', value=$value)"
  }
}


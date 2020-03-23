package co.takan.server

import co.takan.server.data.game.model.Tile
import co.takan.server.data.game.model.TileType
import co.takan.server.data.game.model.numberOfTiles
import co.takan.server.data.game.repo.RoadRepository
import co.takan.server.data.game.repo.StructureRepository
import co.takan.server.data.game.repo.TileRepository
import org.slf4j.LoggerFactory
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean

private val logger by lazy { LoggerFactory.getLogger("GamerServer") }

private fun makeTiles(type: TileType): List<Tile> {
  return (0 until type.numberOfTiles).map { Tile(type = type.name) }
}

fun restartGame(
    tileRepo: TileRepository,
    roadRepo: RoadRepository,
    structureRepo: StructureRepository
) {
  structureRepo.deleteAll()
  roadRepo.deleteAll()
  tileRepo.deleteAll()

  /* generate random board */
  val chits = listOf(2, 3, 3, 4, 4, 5, 5, 6, 6, 8, 8, 9, 9, 10, 10, 11, 11, 12).shuffled().toMutableList()
  val landTiles = TileType.landTiles.flatMap(::makeTiles).shuffled().toList()
  landTiles.forEach {
    it.value = if (chits.isNotEmpty()) chits.removeAt(0) else 0
  }
  tileRepo.saveAll(landTiles)
}

@SpringBootApplication
class GameServerApp {

  @Bean
  fun demo(
      tileRepo: TileRepository,
      roadRepo: RoadRepository,
      structureRepo: StructureRepository
  ) = CommandLineRunner { restartGame(tileRepo, roadRepo, structureRepo) }
}

fun main(args: Array<String>) {
  runApplication<GameServerApp>(*args)
}



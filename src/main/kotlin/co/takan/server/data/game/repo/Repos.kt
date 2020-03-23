package co.takan.server.data.game.repo

import co.takan.server.data.game.model.Road
import co.takan.server.data.game.model.Structure
import co.takan.server.data.game.model.Tile
import org.springframework.data.repository.CrudRepository


interface TileRepository: CrudRepository<Tile, Long> {

}

interface RoadRepository: CrudRepository<Road, Long>

interface StructureRepository: CrudRepository<Structure, Long>
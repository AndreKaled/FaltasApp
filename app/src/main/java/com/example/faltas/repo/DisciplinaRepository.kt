import com.example.faltas.db.DisciplinaDao
import com.example.faltas.model.Disciplina
import com.example.faltas.model.toEntity
import com.example.faltas.model.toModel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

class DisciplinaRepository(private val dao: DisciplinaDao) {

    val allDisciplinas: kotlinx.coroutines.flow.Flow<List<Disciplina>> =
        dao.getAll().map { entities ->
            entities.map { it.toModel() }
        }

    suspend fun insert(disciplina: Disciplina) {
        dao.insert(disciplina.toEntity())
    }

    suspend fun atualizarFaltas(id: Int, incrementar: Boolean) {
        val todas = dao.getAll().first()
        todas.find { it.id == id }?.let { entity ->
            val novoValor = if (incrementar) entity.faltas + 1 else (entity.faltas - 1).coerceAtLeast(0)
            dao.update(entity.copy(faltas = novoValor))
        }
    }
}
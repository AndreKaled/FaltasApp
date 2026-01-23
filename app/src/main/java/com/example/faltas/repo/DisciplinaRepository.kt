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

    suspend fun adicionar(disciplina: Disciplina) {
        dao.insert(disciplina.toEntity())
    }

    suspend fun atualizarFaltas(disciplina: Disciplina, incrementar: Boolean) {
        val novoValor = if (incrementar) disciplina.faltas + 1 else (disciplina.faltas - 1).coerceAtLeast(0)
        dao.update(disciplina.toEntity().copy(faltas = novoValor))
    }

    suspend fun deletar(disciplina: Disciplina){
        dao.delete(disciplina.toEntity())
    }
}
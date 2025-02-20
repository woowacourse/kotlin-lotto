import kotlin.random.Random

class LottoService(val random:Random) {
    fun getLotto():List<Int> {
        return listOf(random.nextInt(), )
    }
}
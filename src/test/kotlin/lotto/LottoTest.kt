package lotto

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class Lotto(
    private val numbers: List<LottoNumber> = generateLotto(),
) {
    init {
        require(numbers.size == 6)
        require(numbers.size == numbers.toSet().size)
    }

    fun getSize() = numbers.size

    companion object {
        private fun generateLotto(): List<LottoNumber> =
            (1..45)
                .shuffled()
                .take(6)
                .sorted()
                .map { LottoNumber(it) }
    }
}

class LottoTest {
    @Test
    fun `로또는 한 장에 로또 번호 6개를 가진다`() {
        val lotto = Lotto()
        assertEquals(lotto.getSize(), 6)
    }

    @Test
    fun `로또 번호는 서로 중복되지 않는다`() {
        assertThrows<IllegalArgumentException> {
            Lotto(
                listOf(
                    LottoNumber(1),
                    LottoNumber(2),
                    LottoNumber(3),
                    LottoNumber(4),
                    LottoNumber(5),
                    LottoNumber(5),
                ),
            )
        }
    }
}

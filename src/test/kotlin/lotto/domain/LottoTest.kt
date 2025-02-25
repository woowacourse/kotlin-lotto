package lotto.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class LottoTest {
    @Test
    fun `로또 번호가 중복되면 예외가 발생한다`() {
        val lottoNumbers = listOf(1, 1, 2, 2, 3, 3)
        assertThrows<IllegalArgumentException> {
            Lotto(lottoNumbers.map { LottoNumber(it) })
        }
    }

    @Test
    fun `로또 번호는 오름차순으로 정렬한다`() {
        val lottoNumbers = listOf(6, 5, 4, 3, 2, 1)
        val lotto = Lotto(lottoNumbers.map { LottoNumber(it) })
        assertThat(lotto.numbers.map { it.number }).isSorted
    }

    @ValueSource(ints = [1, 2, 3, 4, 5])
    @ParameterizedTest
    fun `당첨 번호가 6개가 아니면 예외가 발생한다`(input: Int) {
        val testList = List(input) { 1 }
        assertThrows<IllegalArgumentException> {
            Lotto(testList.map { LottoNumber(it) })
        }
    }
}

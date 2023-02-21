package lotto.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class LottoTest {
    @Test
    fun `로또 번호는 여섯개이다`() {
        assertThrows<IllegalArgumentException> {
            makeLotto(listOf(1, 2, 3, 4, 5))
        }
    }

    @Test
    fun `로또 번호는 중복되지 않는다`() {
        assertThrows<IllegalArgumentException> {
            makeLotto(listOf(4, 3, 1, 2, 2, 2))
        }
    }

    @ParameterizedTest
    @CsvSource(value = ["1,2,3,7,8,9:3", "1,2,3,4,5,6:6"], delimiter = ':')
    fun `로또 번호가 당첨 번호와 몇개 일치 하는지 판단 한다`(numbers: String, matchCount: Int) {
        val lotto = makeLotto(numbers.split(",").map { it.toInt() })
        val winningLotto = makeLotto(listOf(1, 2, 3, 4, 5, 6))

        val actual = lotto.countMatchingNumbers(winningLotto)

        assertThat(actual).isEqualTo(matchCount)
    }

    @ParameterizedTest
    @CsvSource(value = ["1,2,3,4,5,6:3:true", "1,2,3,4,5,6:7:false"], delimiter = ':')
    fun `로또 번호 중 보너스 번호와 일치하는 번호가 있는지 판단한다`(numbers: String, bonusNumber: Int, expected: Boolean) {
        val lotto = makeLotto(numbers.split(",").map { it.toInt() })
        val actual = lotto.checkMatchingBonusNumber(LottoNumber.from(bonusNumber))
        assertThat(actual).isEqualTo(expected)
    }

    private fun makeLotto(numbers: List<Int>): Lotto {
        return Lotto(numbers.map { LottoNumber.from(it) })
    }
}

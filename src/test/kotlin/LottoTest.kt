import domain.Lotto
import domain.LottoNumber
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatIllegalArgumentException
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.CsvSource
import org.junit.jupiter.params.provider.MethodSource

class LottoTest {
    @Test
    fun `로또가 6개의 숫자로 잘 생성되었는지 확인`() {
        assertDoesNotThrow {
            Lotto(1, 2, 3, 4, 5, 6)
        }
    }

    @Test
    fun `로또가 6개의 숫자로 이루어지지 않은 경우 예외가 발생한다`() {
        assertThatIllegalArgumentException()
            .isThrownBy { Lotto(1, 2, 3, 4, 5) }
            .withMessageContaining("로또 번호가 6개가 아닙니다")
    }

    @Test
    fun `중복된 번호가 존재하는 경우 예외가 발생한다`() {
        assertThatIllegalArgumentException()
            .isThrownBy { Lotto(1, 2, 3, 4, 5, 5) }
            .withMessageContaining("로또 번호가 6개가 아닙니다")
    }

    @MethodSource("produceLotto")
    @ParameterizedTest
    fun `로또와 당첨번호 일치하는 개수 확인`(winningLotto: Lotto, matchCount: Int) {
        val lotto = Lotto(1, 2, 3, 4, 5, 6)
        assertThat(lotto.countSameLottoNumber(winningLotto)).isEqualTo(matchCount)
    }

    @ParameterizedTest
    @CsvSource(value = ["6,true", "40,false"])
    fun `보너스 번호가 로또 번호에 포함되어 있는지 확인`(number: Int, hasBonus: Boolean) {
        val lotto = Lotto(1, 2, 3, 4, 5, 6)
        val bonus = LottoNumber.from(number)

        assertThat(lotto.containsNumber(bonus))
    }

    companion object {
        @JvmStatic
        fun produceLotto(): List<Arguments> {
            return listOf(
                Arguments.of(Lotto(1, 2, 3, 10, 20, 30), 3),
                Arguments.of(Lotto(1, 2, 3, 4, 10, 20), 4),
                Arguments.of(Lotto(1, 2, 3, 4, 5, 10), 5),
                Arguments.of(Lotto(1, 2, 3, 4, 5, 6), 6),
            )
        }
    }
}

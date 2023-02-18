import domain.*
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.internal.bytebuddy.asm.Advice.Argument
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import org.junit.jupiter.params.provider.ValueSource

class LottoTest {

    @MethodSource("produceLotto")
    @ParameterizedTest
    fun `로또가 6개의 숫자로 잘 생성되었는지 확인`(numbers: List<LottoNumber>) {
        assertDoesNotThrow {
            Lotto(numbers)
        }
    }
    @MethodSource("produceWrongSizeLotto")
    @ParameterizedTest
    fun `로또가 6개의 숫자로 이루어지지 않은 경우 예외가 발생한다`(numbers: List<LottoNumber>) {
        assertThrows<IllegalArgumentException> {
            Lotto(numbers)
        }
    }

    @MethodSource
    @ParameterizedTest
    fun `중복된 번호가 존재하는 경우`() {
        assertThrows<IllegalArgumentException> {
            Lotto(
                listOf(1, 2, 3, 4, 5, 5),
            )
        }
    }

    /*
    @Test
    fun `로또 당첨번호와 생성된 로또를 비교하여 일치하는 개수 확인`() {
        val testLotto = Lotto(listOf(1, 2, 3, 4, 5, 6))
        val winnigLotto = Lotto(listOf(1, 3, 5, 7, 10, 25))

        assertThat(testLotto.countMatchNumber(winnigLotto)).isEqualTo(3)
    }

    @Test
    fun `생성된 로또에 보너스 번호가 존재하는지 확인`() {
        val testLotto = Lotto(listOf(1, 2, 3, 4, 5, 6))
        val bonusNumber = BonusNumber(3)

        assertThat(testLotto.hasBonusNumber(bonusNumber)).isEqualTo(true)
    }

    @Test
    fun `당첨된 로또가 5개 일치하고 보너스 번호도 일치할 때 2등인지 확인`() {
        val testLotto = Lotto(listOf(1, 2, 3, 4, 5, 6))
        val winnigLotto = Lotto(listOf(1, 2, 3, 4, 5, 10))
        val bonusNumber = BonusNumber(6)

        assertThat(testLotto.matchLotto(winnigLotto, bonusNumber)).isEqualTo(Rank.SECOND)
    }*/

    companion object {

        @JvmStatic
        fun produceLotto(): List<Arguments>{
            return listOf(
                Arguments.of(listOf(1,3,4,6,10,22).map { LottoNumber.from(it) })
            )
        }

        @JvmStatic
        fun produceWrongSizeLotto(): List<Arguments> {
            return listOf(
                Arguments.of((1..5).map { LottoNumber.from(it) }),
                Arguments.of((1..7).map { LottoNumber.from(it) })
            )
        }
    }
}

import domain.Lotto
import domain.LottoNumber
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatIllegalArgumentException
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

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
            .withMessageContaining("당첨 번호가 6개가 아닙니다")
    }

    @Test
    fun `중복된 번호가 존재하는 경우 예외가 발생한다`() {
        assertThatIllegalArgumentException()
            .isThrownBy { Lotto(1, 2, 3, 4, 5, 5) }
            .withMessageContaining("당첨 번호가 6개가 아닙니다")
    }

    @Test
    fun `로또와 당첨번호가 일치하는 개수가 3개인지 확인`() {
        val lotto = Lotto(1, 2, 3, 4, 5, 6)
        val winningLotto = Lotto(1, 2, 3, 10, 20, 30)

        assertThat(lotto.countSameLottoNumber(winningLotto)).isEqualTo(3)
    }

    @Test
    fun `로또와 당첨번호가 일치하는 개수가 4개인지 확인`() {
        val lotto = Lotto(1, 2, 3, 4, 5, 6)
        val winningLotto = Lotto(1, 2, 3, 4, 10, 20)

        assertThat(lotto.countSameLottoNumber(winningLotto)).isEqualTo(4)
    }

    @Test
    fun `로또와 당첨번호가 일치하는 개수가 5개인지 확인`() {
        val lotto = Lotto(1, 2, 3, 4, 5, 6)
        val winningLotto = Lotto(1, 2, 3, 4, 5, 10)

        assertThat(lotto.countSameLottoNumber(winningLotto)).isEqualTo(5)
    }

    @Test
    fun `로또와 당첨번호가 일치하는 개수가 6개인지 확인`() {
        val lotto = Lotto(1, 2, 3, 4, 5, 6)
        val winningLotto = Lotto(1, 2, 3, 4, 5, 6)

        assertThat(lotto.countSameLottoNumber(winningLotto)).isEqualTo(6)
    }

    @ParameterizedTest
    @CsvSource(value = ["6,true", "40,false"])
    fun `보너스 번호가 로또 번호에 포함되어 있는지 확인`(number: Int, hasBonus: Boolean) {
        val lotto = Lotto(1, 2, 3, 4, 5, 6)
        val bonus = LottoNumber.from(number)

        assertThat(lotto.hasBonusNumber(bonus))
    }
}

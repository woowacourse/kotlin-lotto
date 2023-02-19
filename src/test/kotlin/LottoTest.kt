import domain.BonusNumber
import domain.Lotto
import domain.LottoNumber
import domain.Rank
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class LottoTest {
    @Test
    fun `로또가 6개의 숫자로 잘 생성되었는지 확인`() {
        assertThat(testLotto.numbers).isEqualTo(testLotto)
    }

    @Test
    fun `길이가 6이 아닌 경우 예외처리 확인`() {
        assertThrows<IllegalArgumentException> {
            Lotto(
                listOf(
                    LottoNumber.from(1), LottoNumber.from(2),
                    LottoNumber.from(3), LottoNumber.from(4),
                    LottoNumber.from(5), LottoNumber.from(6),
                    LottoNumber.from(7)
                )
            )
        }
    }

    @Test
    fun `중복된 번호가 존재하는 경우 예외처리 확인`() {
        assertThrows<IllegalArgumentException> {
            Lotto(
                listOf(
                    LottoNumber.from(1), LottoNumber.from(2),
                    LottoNumber.from(3), LottoNumber.from(4),
                    LottoNumber.from(5), LottoNumber.from(5)
                )
            )
        }
    }

    @Test
    fun `로또 번호가 1애서 45사이의 숫자가 아닌 경우 예외 처리 확인`() {
        assertThrows<IllegalArgumentException> {
            Lotto(
                listOf(
                    LottoNumber.from(1), LottoNumber.from(12),
                    LottoNumber.from(55), LottoNumber.from(44),
                    LottoNumber.from(23), LottoNumber.from(65)
                )
            )
        }
    }

    @Test
    fun `로또 당첨번호와 생성된 로또를 비교하여 일치하는 개수 확인`() {
        val testLotto = testLotto
        val winnigLotto = Lotto(
            listOf(
                LottoNumber.from(1), LottoNumber.from(3),
                LottoNumber.from(5), LottoNumber.from(7),
                LottoNumber.from(10), LottoNumber.from(25)
            )
        )
        assertThat(testLotto.countMatchNumber(winnigLotto)).isEqualTo(3)
    }

    @Test
    fun `생성된 로또에 보너스 번호가 존재하는지 확인`() {
        val testLotto = testLotto
        val bonusNumber = BonusNumber(LottoNumber.from(3))
        assertThat(testLotto.hasBonusNumber(bonusNumber)).isEqualTo(true)
    }

    @Test
    fun `당첨된 로또가 5개 일치하고 보너스 번호도 일치할 때 2등인지 확인`() {
        val testLotto = testLotto
        val winnigLotto = Lotto(
            listOf(
                LottoNumber.from(1), LottoNumber.from(2),
                LottoNumber.from(3), LottoNumber.from(4),
                LottoNumber.from(5), LottoNumber.from(10)
            )
        )
        val bonusNumber = BonusNumber(LottoNumber.from(6))

        assertThat(testLotto.matchLotto(winnigLotto, bonusNumber)).isEqualTo(Rank.SECOND)
    }

    @Test
    fun `당첨된 로또가 5개 일치하고 보너스 번호도 일치하지 않을 때 3등인지 확인`() {
        val testLotto = testLotto
        val winnigLotto = Lotto(
            listOf(
                LottoNumber.from(1), LottoNumber.from(2),
                LottoNumber.from(3), LottoNumber.from(4),
                LottoNumber.from(5), LottoNumber.from(10)
            )
        )
        val bonusNumber = BonusNumber(LottoNumber.from(22))

        assertThat(testLotto.matchLotto(winnigLotto, bonusNumber)).isEqualTo(Rank.THIRD)
    }

    companion object {
        val testLotto = Lotto(
            listOf(
                LottoNumber.from(1), LottoNumber.from(2),
                LottoNumber.from(3), LottoNumber.from(4),
                LottoNumber.from(5), LottoNumber.from(6)
            )
        )
    }
}

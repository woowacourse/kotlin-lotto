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
    fun `당첨된 로또가 5개 일치하고 보너스 번호도 일치할 때 2등인지 확인`() {
        // given
        // Lotto 1 = 1,2,3,4,5,6
        // Lotto 2 = 1,2,3,4,5,10
        // bonusNumber = 6
        val testLotto = testLotto
        val winnigLotto = Lotto(
            listOf(
                LottoNumber.from(1), LottoNumber.from(2),
                LottoNumber.from(3), LottoNumber.from(4),
                LottoNumber.from(5), LottoNumber.from(10)
            )
        )
        val bonusNumber = LottoNumber.from(6)

        // when
        val result = testLotto.matchLotto(winnigLotto, bonusNumber)

        // then
        assertThat(result).isEqualTo(Rank.SECOND) // 2등 당첨
    }

    @Test
    fun `당첨된 로또가 5개 일치하고 보너스 번호도 일치하지 않을 때 3등인지 확인`() {
        // given
        // Lotto 1 = 1,2,3,4,5,6
        // Lotto 2 = 1,2,3,4,5,10
        // bonusNumber = 22
        val testLotto = testLotto
        val winnigLotto = Lotto(
            listOf(
                LottoNumber.from(1), LottoNumber.from(2),
                LottoNumber.from(3), LottoNumber.from(4),
                LottoNumber.from(5), LottoNumber.from(10)
            )
        )
        val bonusNumber = LottoNumber.from(22)

        // when
        val result = testLotto.matchLotto(winnigLotto, bonusNumber)

        // then
        assertThat(result).isEqualTo(Rank.THIRD) // 3등 당첨
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


import domain.BonusNumber
import domain.Lotto
import domain.RandomLottoGenerator
import domain.Rank
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class LottoTest {

    private val lotto = Lotto(
        listOf(1, 2, 3, 4, 5, 6)
    )
    @Test
    fun `로또가 6개의 숫자로 잘 생성되었는지 확인`() {
        assertThat(lotto.numbers).isEqualTo(listOf(1, 2, 3, 4, 5, 6))
    }

    @Test
    fun `랜덤으로 생성된 로또가 6개의 숫자로 이루어졌는지 확인`() {
        val lotto = RandomLottoGenerator().generateLotto()
        assertThat(lotto.numbers.size).isEqualTo(6)
    }

    @Test
    fun `길이가 6이 아닌 경우`() {
        assertThrows<IllegalArgumentException> {
            Lotto(
                listOf(
                    1, 2, 3, 4, 5, 6, 7
                )
            )
        }
    }

    @Test
    fun `중복된 번호가 존재하는 경우`() {
        assertThrows<IllegalArgumentException> {
            Lotto(
                listOf(1, 2, 3, 4, 5, 5)
            )
        }
    }

    @Test
    fun `로또 번호가 1애서 45사이의 숫자가 아닌 경우`() {
        assertThrows<IllegalArgumentException> {
            Lotto(listOf(1, 12, 55, 44, 23, 65))
        }
    }

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
    }
}

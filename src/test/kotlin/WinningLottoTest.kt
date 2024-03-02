import model.Lotto
import model.LottoNumber
import model.WinningLotto
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class WinningLottoTest {
    private lateinit var winningLotto: WinningLotto

    @BeforeEach
    fun setUp() {
        val winningNumbers = listOf(1, 2, 3, 4, 5, 6)
        val bonusNumber = LottoNumber.from("7")
        winningLotto = WinningLotto(winningNumbers.map { LottoNumber(it) }, bonusNumber)
    }

    @Test
    fun `로또 번호 당첨 개수 일치 확인`() {
        val publishedLotto = Lotto(listOf(1, 2, 3, 4, 5, 8).map { LottoNumber(it) })

        val actual = winningLotto.calculateCountOfMatch(publishedLotto)
        val expected = 5

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `로또 번호 당첨 개수 불일치 확인`() {
        val publishedLotto = Lotto(listOf(1, 2, 3, 4, 5, 8).map { LottoNumber(it) })

        val actual = winningLotto.calculateCountOfMatch(publishedLotto)
        val expected = 3

        assertThat(actual).isNotEqualTo(expected)
    }

    @Test
    fun `보너스 번호 일치 확인`() {
        val publishedLotto = Lotto(listOf(1, 2, 3, 4, 5, 7).map { LottoNumber(it) })

        val actual = winningLotto.checkBonusNumberMatched(publishedLotto)
        val expected = true

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `보너스 번호 불일치 확인`() {
        val publishedLotto = Lotto(listOf(1, 2, 3, 4, 5, 8).map { LottoNumber(it) })

        val actual = winningLotto.checkBonusNumberMatched(publishedLotto)
        val expected = false

        assertThat(actual).isEqualTo(expected)
    }
}

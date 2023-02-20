import domain.Lotto
import domain.LottoNumber
import domain.Rank
import domain.WinningLotto
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatIllegalArgumentException
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

class LottoTest {

    @MethodSource("produceLotto")
    @Test
    fun `로또가 6개의 숫자로 잘 생성되었는지 확인`(numbers: List<LottoNumber>) {
        assertDoesNotThrow {
            Lotto(numbers)
        }
    }

    @MethodSource("produceWrongSizeLotto")
    @Test
    fun `로또가 6개의 숫자로 이루어지지 않은 경우 예외가 발생한다`(numbers: List<LottoNumber>) {
        assertThatIllegalArgumentException()
            .isThrownBy { Lotto(numbers) }
            .withMessageContaining("당첨 번호가 6개가 아닙니다")
    }

    @MethodSource("produceOverlayLotto")
    @Test
    fun `중복된 번호가 존재하는 경우 예외가 발생한다`(numbers: List<LottoNumber>) {
        assertThatIllegalArgumentException()
            .isThrownBy { Lotto(numbers) }
            .withMessageContaining("당첨 번호가 중복되었습니다.")
    }

    /**
     * 생성된 로또 Lotto(1, 2, 3, 4, 5, 6)
     * 당첨 로또 Lotto(1, 2, 3, 7, 8, 9)
     */
    @Test
    fun `생성된 로또가 당첨 로또와 3개 일치하면 5등인지 확인`() {
        val lotto = Lotto((1..6).map { LottoNumber.from(it) })
        val winningLotto = Lotto(listOf(1, 2, 3, 7, 8, 9).map { LottoNumber.from(it) })
        val bonusNumber = LottoNumber.from(20)

        val actual = lotto.matchLotto(WinningLotto(winningLotto, bonusNumber))
        assertThat(actual).isEqualTo(Rank.FIFTH)
    }

    /**
     * 생성된 로또 Lotto(1, 2, 3, 4, 5, 6)
     * 당첨 로또 Lotto(1, 2, 3, 4, 8, 9)
     */
    @Test
    fun `생성된 로또가 당첨 로또와 4개 일치하면 4등인지 확인`() {
        val lotto = Lotto((1..6).map { LottoNumber.from(it) })
        val winningLotto = Lotto(listOf(1, 2, 3, 4, 8, 9).map { LottoNumber.from(it) })
        val bonus = LottoNumber.from(20)

        val actual = lotto.matchLotto(WinningLotto(winningLotto, bonus))
        assertThat(actual).isEqualTo(Rank.FOURTH)
    }

    /**
     * 생성된 로또 Lotto(1, 2, 3, 4, 5, 6)
     * 당첨 로또 Lotto(1, 2, 3, 4, 5, 9)
     * 보너스 번호 20
     */
    @Test
    fun `생성된 로또가 당첨 로또와 5개 일치하고 보너스 번호가 포함되지 않으면, 3등인지 확인`(lotto: Lotto, winningLotto: WinningLotto) {
        val lotto = Lotto((1..6).map { LottoNumber.from(it) })
        val winningLotto = Lotto(listOf(1, 2, 3, 4, 5, 9).map { LottoNumber.from(it) })
        val bonus = LottoNumber.from(20)

        val actual = lotto.matchLotto(WinningLotto(winningLotto, bonus))
        assertThat(actual).isEqualTo(Rank.THIRD)
    }

    /**
     * 생성된 로또 Lotto(1, 2, 3, 4, 5, 6)
     * 당첨 로또 Lotto(1, 2, 3, 4, 5, 9)
     * 보너스 번호 6
     */
    @Test
    fun `생성된 로또가 당첨 로또와 5개 일치하고 보너스 번호가 포함되면, 2등인지 확인`(lotto: Lotto, winningLotto: WinningLotto) {
        val lotto = Lotto((1..6).map { LottoNumber.from(it) })
        val winningLotto = Lotto(listOf(1, 2, 3, 4, 5, 9).map { LottoNumber.from(it) })
        val bonus = LottoNumber.from(6)

        val actual = lotto.matchLotto(WinningLotto(winningLotto, bonus))
        assertThat(actual).isEqualTo(Rank.SECOND)
    }

    /**
     * 생성된 로또 Lotto(1, 2, 3, 4, 5, 6)
     * 당첨 로또 Lotto(1, 2, 3, 4, 5, 6)
     */
    @Test
    fun `생성된 로또가 당첨 로또와 6개 일치하면, 1등인지 확인`(lotto: Lotto, winningLotto: WinningLotto) {
        val lotto = Lotto((1..6).map { LottoNumber.from(it) })
        val winningLotto = Lotto(listOf(1, 2, 3, 4, 5, 6).map { LottoNumber.from(it) })
        val bonus = LottoNumber.from(20)

        val actual = lotto.matchLotto(WinningLotto(winningLotto, bonus))
        assertThat(actual).isEqualTo(Rank.FIRST)
    }

    companion object {

        @JvmStatic
        fun produceLotto(): List<Arguments> {
            return listOf(
                Arguments.of(listOf(1, 3, 4, 6, 10, 22).map { LottoNumber.from(it) }),
            )
        }

        @JvmStatic
        fun produceWrongSizeLotto(): List<Arguments> {
            return listOf(
                Arguments.of((1..5).map { LottoNumber.from(it) }),
                Arguments.of((1..7).map { LottoNumber.from(it) }),
            )
        }

        @JvmStatic
        fun produceOverlayLotto(): List<Arguments> {
            return listOf(
                Arguments.of(listOf(1, 1, 2, 3, 4, 5).map { LottoNumber.from(it) }),
                Arguments.of(listOf(1, 10, 10, 20, 25, 33).map { LottoNumber.from(it) }),
            )
        }
    }
}

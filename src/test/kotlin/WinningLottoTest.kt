import domain.Lotto
import domain.LottoNumber
import domain.WinningLotto
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.CsvSource
import org.junit.jupiter.params.provider.MethodSource

class WinningLottoTest {
    @ParameterizedTest
    @MethodSource("produceOverlayBonusNumber")
    fun `보너스 번호가 당첨번호와 중복이 있을 경우 예외가 발생한다`(winningLotto: Lotto, bonus: LottoNumber) {
        assertThrows<IllegalArgumentException> {
            WinningLotto(winningLotto, bonus)
        }
    }

    @Test
    fun `로또와 당첨번호가 일치하는 개수가 3개인지 확인`() {
        val lotto = (1..6).map { LottoNumber.from(it) }
        val winningLotto = Lotto(listOf(1, 2, 3, 10, 20, 30).map { LottoNumber.from(it) })
        val bonus = LottoNumber.from(40)

        assertThat(WinningLotto(winningLotto, bonus).countSameLottoNumber(lotto)).isEqualTo(3)
    }

    @Test
    fun `로또와 당첨번호가 일치하는 개수가 4개인지 확인`() {
        val lotto = (1..6).map { LottoNumber.from(it) }
        val winningLotto = Lotto(listOf(1, 2, 3, 4, 10, 20).map { LottoNumber.from(it) })
        val bonus = LottoNumber.from(40)

        assertThat(WinningLotto(winningLotto, bonus).countSameLottoNumber(lotto)).isEqualTo(4)
    }

    @Test
    fun `로또와 당첨번호가 일치하는 개수가 5개인지 확인`() {
        val lotto = (1..6).map { LottoNumber.from(it) }
        val winningLotto = Lotto(listOf(1, 2, 3, 4, 5, 10).map { LottoNumber.from(it) })
        val bonus = LottoNumber.from(40)

        assertThat(WinningLotto(winningLotto, bonus).countSameLottoNumber(lotto)).isEqualTo(5)
    }

    @Test
    fun `로또와 당첨번호가 일치하는 개수가 6개인지 확인`() {
        val lotto = (1..6).map { LottoNumber.from(it) }
        val winningLotto = Lotto((1..6).map { LottoNumber.from(it) })
        val bonus = LottoNumber.from(40)

        assertThat(WinningLotto(winningLotto, bonus).countSameLottoNumber(lotto)).isEqualTo(6)
    }

    @ParameterizedTest
    @CsvSource(value = ["6,true", "40,false"])
    fun `보너스 번호가 로또 번호에 포함되어 있는지 확인`(number: Int, hasBonus: Boolean) {
        val lotto = (1..6).map { LottoNumber.from(it) }
        val winningLotto = Lotto(listOf(1, 2, 3, 10, 20, 30).map { LottoNumber.from(it) })
        val bonus = LottoNumber.from(number)

        assertThat(WinningLotto(winningLotto, bonus).hasBonusNumber(lotto)).isEqualTo(hasBonus)
    }

    companion object {
        @JvmStatic
        fun produceOverlayBonusNumber(): List<Arguments> {
            return listOf(
                Arguments.of(Lotto((1..6).map { LottoNumber.from(it) }), LottoNumber.from(6)),
                Arguments.of(Lotto(listOf(1, 5, 10, 15, 20, 30).map { LottoNumber.from(it) }), LottoNumber.from(15)),
            )
        }
    }
}

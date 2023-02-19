import domain.model.LottoResult
import domain.model.WinningLotto
import domain.model.lotto.Lotto
import domain.model.lotto.LottoNumber
import org.assertj.core.api.Java6Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows

class LottoTest {

    @Test
    fun `로또의 번호가 6개인 경우`() {
        val numbers = setOf(
            LottoNumber.from(1),
            LottoNumber.from(2),
            LottoNumber.from(3),
            LottoNumber.from(4),
            LottoNumber.from(5),
            LottoNumber.from(6)
        )

        assertDoesNotThrow {
            Lotto(numbers)
        }
    }

    @Test
    fun `로또의 번호가 6개가 아닌 경우 예외가 발생`() {
        val numbers = setOf(
            LottoNumber.from(1),
            LottoNumber.from(2),
            LottoNumber.from(3),
            LottoNumber.from(4),
            LottoNumber.from(5),
            LottoNumber.from(6),
            LottoNumber.from(7)
        )

        assertThrows<IllegalArgumentException> {
            Lotto(numbers)
        }
    }

    @Test
    fun `로또의 번호가 6개가 일치하는 경우 (1등)`() {
        //given
        val numbers = setOf(
            LottoNumber.from(1),
            LottoNumber.from(2),
            LottoNumber.from(3),
            LottoNumber.from(4),
            LottoNumber.from(5),
            LottoNumber.from(6),
        )
        val catchLotto = Lotto(numbers)

        //when
        val actual = catchLotto.getLottoResult(
            WinningLotto(catchLotto, LottoNumber.from(7))
        )

        //then
        assertThat(actual).isEqualTo(LottoResult.FIRST)
    }

    @Test
    fun `로또의 번호가 5개가 일치하고 보너스 번호가 일치하는 경우 (2등)`() {
        //given
        val lotto = Lotto(
            setOf(
                LottoNumber.from(1),
                LottoNumber.from(2),
                LottoNumber.from(3),
                LottoNumber.from(4),
                LottoNumber.from(5),
                LottoNumber.from(6)
            )
        )

        val catchLotto = Lotto(
            setOf(
                LottoNumber.from(1),
                LottoNumber.from(2),
                LottoNumber.from(3),
                LottoNumber.from(4),
                LottoNumber.from(5),
                LottoNumber.from(7)
            )
        )
        val bonusNumber = LottoNumber.from(6)

        val winningLotto = WinningLotto(catchLotto, bonusNumber)

        //when
        val actual = lotto.getLottoResult(winningLotto)

        //then
        assertThat(actual).isEqualTo(LottoResult.SECOND)
    }

    @Test
    fun `로또의 번호가 5개가 일치하고 보너스 번호가 일치하지 않는 경우 (3등)`() {
        //given
        val lotto = Lotto(
            setOf(
                LottoNumber.from(1),
                LottoNumber.from(2),
                LottoNumber.from(3),
                LottoNumber.from(4),
                LottoNumber.from(5),
                LottoNumber.from(6)
            )
        )

        val catchLotto = Lotto(
            setOf(
                LottoNumber.from(1),
                LottoNumber.from(2),
                LottoNumber.from(3),
                LottoNumber.from(4),
                LottoNumber.from(5),
                LottoNumber.from(7)
            )
        )
        val bonusNumber = LottoNumber.from(8)

        val winningLotto = WinningLotto(catchLotto, bonusNumber)

        //when
        val actual = lotto.getLottoResult(winningLotto)

        //then
        assertThat(actual).isEqualTo(LottoResult.THIRD)
    }

    @Test
    fun `로또의 번호가 3개가 일치하는 경우 (5등)`() {
        //given
        val lotto = Lotto(
            setOf(
                LottoNumber.from(1),
                LottoNumber.from(2),
                LottoNumber.from(3),
                LottoNumber.from(4),
                LottoNumber.from(5),
                LottoNumber.from(6),
            )
        )

        val catchLotto = Lotto(
            setOf(
                LottoNumber.from(1),
                LottoNumber.from(2),
                LottoNumber.from(3),
                LottoNumber.from(10),
                LottoNumber.from(11),
                LottoNumber.from(12),
            )
        )
        val bonusNumber = LottoNumber.from(8)

        val winningLotto = WinningLotto(catchLotto, bonusNumber)

        //when
        val actual = lotto.getLottoResult(winningLotto)

        //then
        assertThat(actual).isEqualTo(LottoResult.FIFTH)
    }
}

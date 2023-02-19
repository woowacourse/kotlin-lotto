import domain.LottoNumber
import domain.WinningLotto
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

class WinningLottoTest {
    @ParameterizedTest
    @MethodSource("produceOverlayBonusNumber")
    fun `보너스 번호가 당첨번호와 중복이 있을 경우 예외가 발생한다`(winningLotto: List<LottoNumber>, bonus: LottoNumber) {
        assertThrows<IllegalArgumentException> {
            WinningLotto(winningLotto, bonus)
        }
    }

    companion object {
        @JvmStatic
        fun produceOverlayBonusNumber(): List<Arguments> {
            return listOf(
                Arguments.of((1..6).map { LottoNumber.from(it) }, LottoNumber.from(6)),
                Arguments.of(listOf(1, 5, 10, 15, 20, 30).map { LottoNumber.from(it) }, LottoNumber.from(15)),
            )
        }
    }
}

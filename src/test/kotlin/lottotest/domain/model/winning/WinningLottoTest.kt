package lottotest.domain.model.winning

import lotto.domain.enums.Rank
import lotto.domain.model.Lotto
import lotto.domain.model.winning.WinningLotto
import lotto.domain.valueobject.LottoNumber
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows

class WinningLottoTest {
    private lateinit var winningNumberLotto1to6: Lotto
    private val bonusNumber45: LottoNumber = LottoNumber(45)
    private val bonusNumber1: LottoNumber = LottoNumber(1)

    @BeforeEach
    fun setUp() {
        winningNumberLotto1to6 = Lotto(listOf(1, 2, 3, 4, 5, 6).map { LottoNumber(it) })
    }

    @Test
    fun `당첨 번호와 보너스 번호는 서로 중복되지 않는다`() {
        assertDoesNotThrow {
            WinningLotto(winningNumberLotto1to6, bonusNumber45)
        }
        assertThrows<IllegalArgumentException> {
            WinningLotto(winningNumberLotto1to6, bonusNumber1)
        }
    }

    @Test
    fun `보유한 당첨 정보를 이용해 전달받은 로또 인스턴스의 Rank정보를 반환한다`() {
        // Given
        val lotto = Lotto((2..7).map { LottoNumber(it) })
        val winningLotto = WinningLotto(winningNumberLotto1to6, bonusNumber45)

        // When
        val rank = winningLotto.getRank(lotto)

        // Then
        assertThat(rank).isExactlyInstanceOf(Rank::class.java)
    }
}

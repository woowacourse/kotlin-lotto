package model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow

class LottoGameManagerTest {
    private lateinit var lottoGameManager: LottoGameManager
    private lateinit var autoLottoGenerator: AutoLottoGenerator

    @BeforeEach
    fun setUp() {
        autoLottoGenerator = AutoLottoGenerator()
        lottoGameManager = LottoGameManager(autoLottoGenerator)
    }

    @Test
    fun `유효한 금액이 입력되었다면, 금액에 맞는 개수의 로또가 생성된다`() {
        val expectedSize = 6
        val actualSize = lottoGameManager.generateLotteries(6100).size
        assertThat(actualSize).isEqualTo(expectedSize)
    }

    @Test
    fun `유효한 로또 번호들로만 이뤄진 당첨 번호가 입력되었다면, 우승 로또가 생성된다`() {
        assertDoesNotThrow {
            lottoGameManager.generateWinningLotto(listOf(1, 2, 3, 4, 5, 6))
        }
    }

    @Test
    fun `유효한 우승 로또의 번호와 우승 번호와 중복되지 않는 보너스 번호가 입력되었다면, 보너스 번호를 위한 로또 숫자 객체가 생성된다`() {
        assertDoesNotThrow {
            lottoGameManager.generateBonusLottoNumber(7, Lotto(1, 2, 3, 4, 5, 6))
        }
    }
}

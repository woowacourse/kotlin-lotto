package lotto.model

import lotto.entity.Lotto
import lotto.entity.LottoGame
import lotto.entity.LottoNumber
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test

internal class LottoGeneratorTest {
    companion object {
        private lateinit var lottoGenerator: LottoGenerator

        @BeforeAll
        @JvmStatic
        fun init() {
            val lottoGame = LottoGame(
                listOf(
                    Lotto(listOf(1, 2, 3, 4, 5, 6)),
                )
            )
            lottoGenerator = SequentialLottoNumberGenerator(lottoGame)
        }
    }

    @Test
    fun `생성된 로또는 1,2,3,4,5,6이다`() {
        val lotto = lottoGenerator.generate()
        val lottoNumbers = lotto.numbers.toList()

        val targetLottoNumbers =
            listOf(LottoNumber(1), LottoNumber(2), LottoNumber(3), LottoNumber(4), LottoNumber(5), LottoNumber(6))
        assertThat(lottoNumbers).isEqualTo(targetLottoNumbers)
    }
}

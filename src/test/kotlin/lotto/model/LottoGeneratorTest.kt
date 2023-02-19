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
            val lottos = listOf(
                Lotto(listOf(1, 2, 3, 4, 5, 6)),
            )

            lottoGenerator = SequentialLottoNumberGenerator(lottos)
        }
    }

    @Test
    fun `로또를 하나 생성할때, 생성된 로또는 1,2,3,4,5,6이다`() {
        // when
        val lotto = lottoGenerator.generate()
        val lottoNumbers = lotto.numbers.toList()
        val targetLottoNumbers =
            listOf(
                LottoNumber.from(1),
                LottoNumber.from(2),
                LottoNumber.from(3),
                LottoNumber.from(4),
                LottoNumber.from(5),
                LottoNumber.from(6)
            )

        // then
        assertThat(lottoNumbers).isEqualTo(targetLottoNumbers)
    }
}

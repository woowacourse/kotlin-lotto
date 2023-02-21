package lotto.model

import lotto.entity.Lotto
import lotto.entity.LottoNumber
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test

internal class LottoGeneratorTest {

    @Test
    fun `생성된 로또의 번호는 1,2,3,4,5,6이다`() {
        val expect = Lotto.from(
            listOf(
                LottoNumber(1),
                LottoNumber(2),
                LottoNumber(3),
                LottoNumber(4),
                LottoNumber(5),
                LottoNumber(6)
            )
        )

        assertThat(lottoGenerator.generate() == expect).isTrue
    }

    companion object {
        private lateinit var lottoGenerator: SequentialLottoNumberGenerator

        @BeforeAll
        @JvmStatic
        fun init() {
            val lottos = listOf(
                Lotto.from(
                    listOf(
                        LottoNumber(1),
                        LottoNumber(2),
                        LottoNumber(3),
                        LottoNumber(4),
                        LottoNumber(5),
                        LottoNumber(6)
                    )
                )
            )
            lottoGenerator = SequentialLottoNumberGenerator(lottos)
        }
    }
}

package domain.lottogenerator

import domain.model.lotto.Lotto
import domain.model.lotto.LottoNumber
import org.assertj.core.api.Java6Assertions.assertThat
import org.junit.jupiter.api.Test

class ManualLottoGeneratorTest {

    @Test
    fun `수동으로 로또를 생성하기`() {
        val lotto = ManualLottoGenerator().generate(listOf(1, 2, 3, 4, 5, 6))

        assertThat(lotto).isEqualTo(
            Lotto(
                setOf(
                    LottoNumber.from(1),
                    LottoNumber.from(2),
                    LottoNumber.from(3),
                    LottoNumber.from(4),
                    LottoNumber.from(5),
                    LottoNumber.from(6)
                )
            )
        )
    }
}

package domaintest.lottogeneratortest

import domain.lottogenerator.ManualLottoGenerator
import domain.model.lotto.Lotto
import org.assertj.core.api.Java6Assertions.assertThat
import org.junit.jupiter.api.Test

class ManualLottoGeneratorTest {

    @Test
    fun `수동으로 로또를 생성하기`() {
        val lotto = ManualLottoGenerator().generate(listOf(1, 2, 3, 4, 5, 6))

        assertThat(lotto).isInstanceOf(Lotto::class.java)
    }
}

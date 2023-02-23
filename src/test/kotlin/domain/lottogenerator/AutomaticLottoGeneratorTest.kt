package domain.lottogenerator

import domain.model.lotto.Lotto
import org.assertj.core.api.Java6Assertions.assertThat
import org.junit.jupiter.api.Test

class AutomaticLottoGeneratorTest {

    @Test
    fun `자동 로또를 생성하기`() {
        val lotto = AutomaticLottoGenerator().generate()

        assertThat(lotto).isInstanceOf(Lotto::class.java)
    }
}

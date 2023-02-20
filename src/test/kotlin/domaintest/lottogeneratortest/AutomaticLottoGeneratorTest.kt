package domaintest.lottogeneratortest

import domain.lottogenerator.AutomaticLottoGenerator
import domain.model.lotto.Lotto
import org.assertj.core.api.Java6Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class AutomaticLottoGeneratorTest {

    lateinit var automaticLottoGenerator: AutomaticLottoGenerator

    @BeforeEach
    fun setUp() {
        automaticLottoGenerator = AutomaticLottoGenerator()
    }

    @Test
    fun `자동 로또를 생성하기`() {
        val lotto = automaticLottoGenerator.generate()

        assertThat(lotto).isInstanceOf(Lotto::class.java)
    }
}

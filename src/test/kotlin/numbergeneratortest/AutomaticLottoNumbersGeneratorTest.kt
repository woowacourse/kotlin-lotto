package numbergeneratortest

import domain.AutomaticLottoNumbersGenerator
import domain.model.lotto.LottoNumber
import org.assertj.core.api.Java6Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class AutomaticLottoNumbersGeneratorTest {

    lateinit var automaticLottoNumbersGenerator: AutomaticLottoNumbersGenerator

    @BeforeEach
    fun setUp(){
        automaticLottoNumbersGenerator = AutomaticLottoNumbersGenerator()
    }

    @Test
    fun `자동으로 로또 번호를 생성하기`(){
        val lottoNumbers = automaticLottoNumbersGenerator.generate()

        lottoNumbers.forEach{ lottoNumber ->
            assertThat(lottoNumber).isInstanceOf(LottoNumber::class.java)
        }
    }
}
package winston



import grails.test.mixin.*
import org.junit.*

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Dataset)
class DatasetTests {

    void testToString() {
		def datasetTitle = "Iris dataset"
		Dataset dataset = new Dataset(title: datasetTitle);
		assert dataset.toString() == "${datasetTitle}: null"
    }
}

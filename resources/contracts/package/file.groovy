import org.springframework.cloud.contract.spec.Contract
Contract.make {
    description "Request to find course detail for that student"
    request{
        method GET()
        url("/students/Student2/courses/Course3") 
    }
    response {
		body(file("test_response.json"))
		headers {contentType(applicationJson())}
        status 200
    }
}
import org.springframework.cloud.contract.spec.Contract
Contract.make {
    description "Request to find todo"
    request{
        method GET()
        url("/api/todos/1") 
    }
    response {
		body(file("test_response.json"))
		headers {contentType(applicationJson())}
        status 200
    }
}
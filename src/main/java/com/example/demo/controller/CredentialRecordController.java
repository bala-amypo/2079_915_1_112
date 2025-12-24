@RestController
@RequestMapping("/credentials")
public class CredentialRecordController {

    private final CredentialRecordService service;

    public CredentialRecordController(CredentialRecordService service) {
        this.service = service;
    }

    @PostMapping
    public CredentialRecord create(@RequestBody CredentialRecord c) {
        return service.createCredential(c);
    }

    @GetMapping("/{id}")
    public CredentialRecord getById(@PathVariable Long id) {
        return service.getCredentialById(id);
    }

    @GetMapping
    public List<CredentialRecord> getAll() {
        return service.getAllCredentials();
    }
}

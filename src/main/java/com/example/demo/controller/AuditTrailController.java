@RestController
@RequestMapping("/audit")
public class AuditTrailController {

    private final AuditTrailService service;

    public AuditTrailController(AuditTrailService service) {
        this.service = service;
    }

    @PostMapping
    public AuditTrailRecord log(@RequestBody AuditTrailRecord record) {
        return service.logEvent(record);
    }

    @GetMapping("/credential/{id}")
    public List<AuditTrailRecord> byCredential(@PathVariable Long id) {
        return service.getLogsByCredential(id);
    }
}

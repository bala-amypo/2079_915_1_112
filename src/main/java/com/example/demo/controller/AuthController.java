@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public User register(@Valid @RequestBody User user) {
        return userService.createUser(user);
    }

    @PostMapping("/login")
    public String login(@Valid @RequestBody User requestUser) {

        User dbUser = userService.findByEmail(requestUser.getEmail());

        if (!dbUser.getPassword().equals(requestUser.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }
        return "Login successful";
    }
}

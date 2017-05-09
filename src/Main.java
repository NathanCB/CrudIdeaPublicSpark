import spark.ModelAndView;
import spark.Session;
import spark.Spark;
import spark.template.mustache.MustacheTemplateEngine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Main {
    static Map<String, User> users = new HashMap<>();
    static int idCounter = 0;
    public final static String PUBLIC_VIEW_NAME_AND_TEXTS_HASH = "PUBLIC_VIEW_NAME_AND_TEXTS_HASH";

    public static void main(String[] args) {
        Spark.init();

        Spark.get("/",
                (request, response) -> {
                    HashMap m = new HashMap<>();
                    ArrayList<User> userList = new ArrayList<>();
                    int namekey = 0;

                    //keep track of each user's idea without creating another object and maintaining existing data structure
                    //Create hash that uses "sections" in Mustache
                    HashMap<String, ArrayList<String>> innerUserNameAndTextMap = new HashMap<>();
                    for (User i : users.values()) {
                        if (!i.getName().isEmpty()) {
                            String userName = i.getName();
                            userList.add(i);

                            String userViewName = userList.get(namekey).getName();
                            if (userViewName.equals(userName)) {
                                ArrayList<String> textList = new ArrayList<>();

                                users.get(userViewName).getIdeas().forEach(u -> textList.add(u.getText()));
                                innerUserNameAndTextMap.put(userViewName, textList);

                            }
                            namekey++;
                        }
                    }
                    m.put(PUBLIC_VIEW_NAME_AND_TEXTS_HASH, innerUserNameAndTextMap);
                    System.out.println(m);

                    return new ModelAndView(m, "index.html");
                },
                new MustacheTemplateEngine()
        );

        Spark.get("/home",
                (request, response) -> {
                    HashMap m = new HashMap<>();

                    Session session = request.session();
                    String name = session.attribute("userName");
                    User user = users.get(name);

                    if (user == null) {
                        return new ModelAndView(m, "login.html");
                    } else {
                        return new ModelAndView(user, "home.html");
                    }
                },
                new MustacheTemplateEngine()
        );

        Spark.post("/login",
                (request, response) -> {
                    String name = request.queryParams("loginName");
                    User user = users.get(name);
                    if (user == null) {
                        users.putIfAbsent(name, new User(name));
                    }

                    Session session = request.session();
                    session.attribute("userName", name);
                    response.redirect("/home");
                    return "";
                }
        );

        Spark.post(
                "/logout",
                (request, response) -> {
                    Session session = request.session();
                    session.invalidate();
                    response.redirect("/");
                    return "";
                }
        );

        Spark.post(
                "/create-idea",
                (request, response) -> {
                    Session session = request.session();
                    String name = session.attribute("userName");
                    User user = users.get(name);
                    if (user == null) {
                        throw new Exception("Please login");
                    } else {
                        String ideaInputIdeaFromView = request.queryParams("ideaText");
                        int id = user.ideas.size();

                        Idea idea = new Idea(id++, ideaInputIdeaFromView);
                        users.get(name).ideas.add(idea);

                    }
                    response.redirect("/home");
                    return "";
                }
        );

        Spark.post(
                "/edit-idea",
                (request, response) -> {
                    Session session = request.session();
                    String name = session.attribute("userName");
                    User user = users.get(name);
                    int id = Integer.parseInt(request.queryParams("id"));
                    String text = request.queryParams("editText");

                    Idea idea = new Idea(idCounter++, text);
                    int c = 0;
                    for (Idea i : user.ideas) {
                        if (i.id == id) {
                            user.ideas.set(c, idea);
                            response.redirect("/home");
                            return "";
                        }
                        c++;
                    }
                    response.redirect("/home");
                    return "";
                }
        );

        Spark.post(
                "/delete-idea",
                (request, response) -> {
                    Session session = request.session();
                    String name = session.attribute("userName");
                    User user = users.get(name);

                    int id = Integer.parseInt(request.queryParams("id"));

                    System.out.println("delete id: " + id);
                    for (Idea i : user.ideas) {
                        if (i.id == id) {
                            user.ideas.remove(i);
                            response.redirect("/home");
                            return "";
                        }
                    }
                    return "";
                }
        );
    }
}

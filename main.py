from tkinter import *
from tkinter import filedialog
from PIL import Image

# global variables
file_path = ""

# functions
def browse_files():
    filename = filedialog.askopenfilename(initialdir = "/", title = "Select a File",
                                          filetypes = (("PNG files", "*.png*"), ("JPG files", "*.jpg*"), ("All files", "*.*")))
    label_file_explorer.configure(text="File Opened: " + filename)
    global file_path
    file_path = str(filename)

def black_white():
    if len(file_path) > 3:
        image = Image.open(file_path)
        temp = image.convert("L")
        temp.show()
        del temp
    else:
        label_file_explorer.configure(text="No File Picked!")

def green_blind():
    if len(file_path) > 3:
        image = Image.open(file_path)
        r, g, b = image.split()
        zeroes = g.point(lambda _: 0)
        merge = Image.merge("RGB", (r, zeroes, b))
        merge.show()
        del merge
    else:
        label_file_explorer.configure(text="No File Picked!")

def red_blind():
    if len(file_path) > 3:
        image = Image.open(file_path)
        r, g, b = image.split()
        zeroes = r.point(lambda _: 0)
        merge = Image.merge("RGB", (zeroes, g, b))
        merge.show()
        del merge
    else:
        label_file_explorer.configure(text="No File Picked!")

def red_green_blind():
    if len(file_path) > 3:
        image = Image.open(file_path)
        r, g, b = image.split()
        zeroes = g.point(lambda _: 120)
        merge = Image.merge("RGB", (zeroes, zeroes, b))
        merge.show()
        del merge
    else:
        label_file_explorer.configure(text="No File Picked!")

def show_img():
    if len(file_path) > 3:
        image = Image.open(file_path)
        image.show()
        del image
    else:
        label_file_explorer.configure(text="No File Picked!")

# main window
root = Tk()
root.title("Color Blind Visualizer")
root.geometry("470x250")

# widgets
button_explore = Button(root, text = "Browse Files", fg = "green", command = browse_files)
button_exit = Button(root, text = "Exit", fg = "red", command = exit)
label_file_explorer = Label(root, text = "File Explorer", width = 70, height = 4, fg = "blue")

monochromacy_button = Button(root, text="Monochromacy: Black and White Vision", command=black_white)
deuteranomaly_button = Button(root, text="Deuteranomaly: Green Looks Red", command=green_blind)
protanomaly_button = Button(root, text="Protanomaly: Red Looks Green", command=red_blind)
protanopia_button = Button(root, text="Protanopia: No Differnence Between Red and Green", command=red_green_blind)
normal_button = Button(root, text="Regular Vision", command=show_img)

# widget mapping
label_file_explorer.place(x=0, y=5)
button_explore.place(x=25, y=70)
button_exit.place(x=225, y=70)
monochromacy_button.place(x=30, y=200)
deuteranomaly_button.place(x=25, y=110)
protanomaly_button.place(x=280, y=110)
protanopia_button.place(x=30, y=150)
normal_button.place(x=300, y=200)

# loop
root.mainloop()

